#!/usr/bin/env bash
cd $(dirname $0)
cd ../webclient
pwd
ls -l
npm install

echo "***** START ANGULAR 4 APPLICATION"
npm start &
sleep 120

echo "***** CURL to home url"
curl -s http://localhost:4200

echo "***** END"
exit 0
