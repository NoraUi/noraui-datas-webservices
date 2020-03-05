cd $(dirname $0)
cd ../project
mvn clean -q package
pwd
java -jar target/noraui-datas-webservices-1.0.0-SNAPSHOT.jar &
PID=$!
sleep 30

curl -u admin:secret -s http://localhost:8088/actuator/health > target/actual_health.json
echo "Let's look at the actual health: `cat target/actual_health.json`"
echo "And compare it to: `cat ../test/health.json`"
if diff -w ../test/health.json target/actual_health.json
    then
        echo SUCCESS
        let ret=0
    else
        echo FAIL
        let ret=255
        exit $ret
fi

curl -s --header "Accept: application/json" http://localhost:8084/noraui/api/hello/columns > target/actual_hello_columns.json

echo "after curl json"

curl -s -v --header "Content-Type: application/xml" --header "Accept: application/xml" http://localhost:8084/noraui/api/hello/columns > target/actual_hello_columns.xml

sleep 3
echo "after curl xml"

kill -9 $PID

echo "Let's look at the actual results: `cat target/actual_hello_columns.json`"
echo "And compare it to: `cat ../test/expected_hello_columns.json`"
if diff -w ../test/expected_hello_columns.json target/actual_hello_columns.json
    then
        echo SUCCESS
        let ret=0
    else
        echo FAIL
        let ret=255
        exit $ret
fi

echo "Let's look at the actual results: `cat target/actual_hello_columns.xml`"
echo "And compare it to: `cat ../test/expected_hello_columns.xml`"
if diff -w ../test/expected_hello_columns.xml target/actual_hello_columns.xml
    then
        echo SUCCESS
        let ret=0
    else
        echo FAIL
        let ret=255
        exit $ret
fi

rm -rf target

exit
