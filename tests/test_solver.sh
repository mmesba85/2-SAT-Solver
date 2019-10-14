for file in "$1"/*; do
  echo Test file: $file
  java Main $file
done
