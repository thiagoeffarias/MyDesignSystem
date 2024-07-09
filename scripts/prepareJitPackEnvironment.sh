#!/bin/bash

echo "Verificando versão do Java..."
java -version

# Defina JAVA_HOME se necessário
if [ -z "$JAVA_HOME" ]; then
  export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
  echo "JAVA_HOME está definido como $JAVA_HOME"
else
  echo "JAVA_HOME já está definido como $JAVA_HOME"
fi

echo "Java setup completo."
