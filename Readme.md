grep -o -w '\w\{5,5\}' es.txt > out.txt
cat out.txt  | jq  --raw-input .  | jq --slurp . > out2.txt