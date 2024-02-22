#!/bin/bash
$JBOSS_HOME/bin/standalone.sh -c standalone.xml &

sleep 30

$JBOSS_HOME/bin/jboss-cli.sh --connect --command="module add --name=com.mysql --resources=/opt/jboss/wildfly/bin/mysql-connector-j-8.3.0.jar --dependencies=javax.api,javax.transaction.api"
$JBOSS_HOME/bin/jboss-cli.sh --connect --command="/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql)"
$JBOSS_HOME/bin/jboss-cli.sh --connect --command="data-source add --jndi-name=java:/MySqlDS --name=MySQLPool --connection-url=jdbc:mysql://localhost:3306/test --driver-name=mysql --user-name=root --password=password"

$JBOSS_HOME/bin/jboss-cli.sh --connect --command=":shutdown"

chmod +x customize-wildfly.sh
