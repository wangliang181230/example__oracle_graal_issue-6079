title "ProxyReflectionExample-Build-Native-Image"

javac TestInterface.java ProxyReflectionExample.java -d ..\..\..\target\classes

native-image -classpath ..\..\..\target\classes ^
    ProxyReflectionExample ^
    --no-fallback -H:+PrintImageObjectTree -H:+ExhaustiveHeapScan -H:+PrintAnalysisCallTree ^
    -H:Path=..\..\..\target\ ^
    -H:Name=ProxyReflectionExample ^
    -H:ReflectionConfigurationFiles=./reflect-config.json

cmd
