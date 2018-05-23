#!/usr/bin/env bash
MAXCOUNT=10
count=1

echo
echo "- Start publishing $MAXCOUNT messages"
while [ "$count" -le $MAXCOUNT ]      # Generate 10 ($MAXCOUNT) random integers.
do
  number=$RANDOM
  mosquitto_pub -t myhome/livingroom/temperature -m $number
  let "count += 1"  # Increment count.
done
echo "- Done publishing"