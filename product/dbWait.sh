#!/bin/bash

set -e

export PGPASSWORD=PRODUCT

until psql -h "localhost" -U "PRODUCT" -c '\l'; do
  echo >&2 "Postgres is unavailable - sleeping"
  sleep 1
done

echo >&2 "Postgres is up - executing command"
exec $cmd
