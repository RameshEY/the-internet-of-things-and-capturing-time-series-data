#!/usr/bin/env bash
echo "- Starting subscriber"
mosquitto_sub --cafile aws-iot-rootCA.pem --cert device-certificate.pem.crt --key device-private.pem.key -h a3rb9yk7vawpbz.iot.us-east-1.amazonaws.com -p 8883 -t myhome/livingroom/temperature -q 1