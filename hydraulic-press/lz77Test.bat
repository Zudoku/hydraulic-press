call ./gradlew.bat :jar

java -jar ./build./libs./hydraulic-press.jar testdata1.txt 3
java -jar ./build./libs./hydraulic-press.jar testdata2.txt 3
java -jar ./build./libs./hydraulic-press.jar testdata3.txt 3
java -jar ./build./libs./hydraulic-press.jar testdata4.txt 3
java -jar ./build./libs./hydraulic-press.jar testdata5.txt 3
java -jar ./build./libs./hydraulic-press.jar testdata6.txt 3

java -jar ./build./libs./hydraulic-press.jar testdata1.txt.result 4
java -jar ./build./libs./hydraulic-press.jar testdata2.txt.result 4
java -jar ./build./libs./hydraulic-press.jar testdata3.txt.result 4
java -jar ./build./libs./hydraulic-press.jar testdata4.txt.result 4
java -jar ./build./libs./hydraulic-press.jar testdata5.txt.result 4
java -jar ./build./libs./hydraulic-press.jar testdata6.txt.result 4
