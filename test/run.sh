cd $(dirname $0)
cd ../project
mvn clean package
pwd
java -jar target/noraui-datas-webservices-1.0.0-SNAPSHOT.jar &
PID=$!
sleep 30
curl -s --header "Accept: application/json" http://localhost:8084/noraui/api/hello/columns > target/actual_hello_columns.json
curl -s --header "Accept: application/xml" http://localhost:8084/noraui/api/hello/columns > target/actual_hello_columns.xml
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

cd ../webclient
pwd
ls -l
ng serve
sleep 30
curl -s http://localhost:8084/noraui/api/hello/columns

exit
