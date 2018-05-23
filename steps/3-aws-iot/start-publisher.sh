#!/usr/bin/env bash
MAXCOUNT=10
count=1

echo
echo "- Start publishing $MAXCOUNT messages"
while [ "$count" -le $MAXCOUNT ]      # Generate 10 ($MAXCOUNT) random integers.
do
  number=$RANDOM
  mosquitto_pub --cafile aws-iot-rootCA.pem --cert device-certificate.pem.crt --key device-private.pem.key  -h a3rb9yk7vawpbz.iot.us-east-1.amazonaws.com -p 8883 -t myhome/livingroom/temperature -m $number -q 1
  let "count += 1"  # Increment count.
done
echo "- Done publishing"