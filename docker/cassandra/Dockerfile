FROM cassandra:3.11.2

COPY cassandra-init.sh /cassandra-init.sh
COPY cassandra-health-check.sh /cassandra-health-check.sh

RUN chmod a+x /cassandra-init.sh
RUN chmod a+x /cassandra-health-check.sh