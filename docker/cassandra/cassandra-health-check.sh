#!/bin/bash

TABLE=$(cqlsh -e "select TABLE_NAME from system_schema.tables where keyspace_name='my_retail';")
table_to_check="product_detail"

until [[ $TABLE =~  .*$table_to_check.* ]]; do
  sleep 1
  TABLE=$(cqlsh -e "select TABLE_NAME from system_schema.tables where keyspace_name='my_retail';")
  echo "Still waiting for table to be created"
done