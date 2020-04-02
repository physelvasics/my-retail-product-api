#!/bin/bash

CQL="CREATE KEYSPACE IF NOT EXISTS my_retail WITH REPLICATION = { 'class' : 'org.apache.cassandra.locator.SimpleStrategy','replication_factor': '1'};

CREATE TYPE current_price (
    value double,
    currency_code text
);

 CREATE TABLE IF NOT EXISTS my_retail.product_detail  (
    id        int,
    current_price set<frozen<current_price>>,
    PRIMARY KEY (id)
);


"

until echo $CQL | cqlsh; do
  echo "cqlsh: Cassandra is unavailable - retry later"
  sleep 2
done &

exec /docker-entrypoint.sh "$@"
