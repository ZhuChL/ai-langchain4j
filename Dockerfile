# 第一阶段：构建应用
FROM amazoncorretto:17-alpine3.17 AS build
WORKDIR /app

# 缓存依赖
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 复制源代码并构建
COPY src ./src
RUN mvn clean package -DskipTests

# 第二阶段：运行应用
FROM gcr.io/distroless/java17-debian11 AS runtime
WORKDIR /app

# 从构建阶段复制应用
COPY --from=build /app/target/my-app.jar /app/app.jar

# 设置环境变量
ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseG1GC -XX:MaxMetaspaceSize=128m -XX:+HeapDumpOnOutOfMemoryError -Djava.security.egd=file:/dev/./urandom"
ENV APP_PORT=8080

# 声明端口
EXPOSE $APP_PORT

# 添加健康检查（根据应用调整URL）
HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
  CMD [ "curl", "-f", "http://localhost:${APP_PORT}/actuator/health" ] || exit 1

# 非root用户运行
USER nonroot:nonroot

# 启动应用
CMD ["java", $JAVA_OPTS, "-jar", "app.jar"]