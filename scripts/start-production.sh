cd ..
java -Dfile.encoding=UTF-8 -jar -Xms100m -Xmx100m -XX:MaxPermSize=50m target/movies-0.1.jar server movies-production.yml &
