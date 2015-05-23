cd ..
java -Dfile.encoding=UTF-8 -jar -Xms50m -Xmx50m -XX:MaxPermSize=50m target/movies-0.1.jar server movies-develop.yml &
