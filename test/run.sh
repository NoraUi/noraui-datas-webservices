cd $(dirname $0)
cd ../project
mvn clean package
java -jar target/noraui-datas-webservices-1.0.0-SNAPSHOT.jar &
PID=$!
sleep 15
curl -s http://localhost:8084/noraui/api/hello/columns > target/actual_hello_columns.json
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

rm -rf target

exit
