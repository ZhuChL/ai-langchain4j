# 第一阶段：构建应用
FROM amazoncorretto:17-alpine3.17 AS build

# 安装 Maven
RUN apk add --no-cache maven

WORKDIR /app

# 缓存依赖
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 复制源代码并构建 相当于把代码目录src下文件拷贝到 /app/src下
COPY src ./src
# 或者使用条件判断的方式
RUN if [ ! -d "/usr/local/bin" ]; then \
        mkdir /usr/local/bin; \
        echo "已创建/external目录"; \
    else \
        echo "/usr/local/bin目录已存在"; \
    fi
COPY entrypoint.sh /usr/local/bin/entrypoint.sh
# 检查关键文件是否存在
RUN if [ ! -f "/usr/local/bin/entrypoint.sh" ]; then \
        echo "错误: requirements.txt 文件不存在!"; \
    fi
RUN mvn clean package -DskipTests

# 第二阶段：运行应用
FROM gcr.io/distroless/java17-debian11 AS runtime
WORKDIR /app

# 从构建阶段复制应用
COPY --from=build /app/target/ai-langchain4j*.jar ai-langchain4j.jar

# 设置环境变量
ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseG1GC -XX:MaxMetaspaceSize=128m -XX:+HeapDumpOnOutOfMemoryError -Djava.security.egd=file:/dev/./urandom"
ENV APP_PORT=8080

# 声明端口
EXPOSE $APP_PORT

# 添加健康检查（根据应用调整URL）
HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
  CMD [ "curl", "-f", "http://localhost:${APP_PORT}/actuator/health" ] || exit 1


# 赋予执行权限
RUN chmod +x /usr/local/bin/entrypoint.sh

# 非root用户运行
USER nonroot:nonroot

# 启动应用
ENTRYPOINT ["/usr/local/entrypoint.sh"]
CMD ["java", $JAVA_OPTS, "-jar", "/app/ai-langchain4j.jar"]