# Bu image Java uygulamalarını çalıştırmak için gerekli tüm bileşenleri içerir.
FROM openjdk:22-jdk-slim

VOLUME /tmp
#Bu, Docker konteynerinin içinde çalıştırılacak uygulamanın jar dosyasını sağlamaktadır.
COPY target/orderservice-0.0.1-SNAPSHOT.jar orderservice.jar
#Docker konteyneri başladığında çalıştırılacak komutu belirtir
# yani "java -jar orderservice.jar"
ENTRYPOINT ["java","-jar","/orderservice.jar"]
