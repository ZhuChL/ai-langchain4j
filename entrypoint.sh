#!/bin/sh
set -e  # 命令失败时立即退出

# 环境变量默认值处理
: "${APP_PORT:=8080}"       # 如果未设置 APP_PORT，则默认为 8080
: "${LOG_LEVEL:=INFO}"     # 日志级别默认 INFO

# 输出环境信息（可选）
echo "Starting application with PORT=$APP_PORT and LOG_LEVEL=$LOG_LEVEL"

# 执行初始化操作（如数据库迁移）
if [ "$RUN_MIGRATIONS" = "true" ]; then
    echo "Running database migrations..."
fi

# 启动应用（将 CMD 作为参数传递给 ENTRYPOINT）
echo "Starting main application..."
exec "$@"  # 执行 Dockerfile 中 CMD 指定的命令