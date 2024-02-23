# Dockerfile

# Zulu JDK 17을 베이스 이미지로 사용
FROM azul/zulu-openjdk:17

# 빌드된 JAR 파일을 이미지로 복사하고 이름을 yaksok.jar로 변경
COPY target/*.jar /docker/yaksok.jar

# 작업 디렉토리를 /docker로 설정
WORKDIR /docker

# 애플리케이션 실행
CMD ["java", "-jar", "yaksok.jar"]