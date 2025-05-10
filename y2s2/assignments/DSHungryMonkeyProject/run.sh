echo "Welcome to the hungry_monkey game, how would you like to run today?"

echo "pick your mode of running: (gui,print,score)"
read -r decision

if [[ "$decision" == "gui" ]]; then
  gcc hungry_monkey.c move_monkey.c -o hungry_monkey -lcurses
  ./hungry_monkey
elif [[ "$decision" == "print" ]]; then
  gcc hungry_monkey_printf.c move_monkey.c -o hungry_monkey
  ./hungry_monkey
elif [[ "$decision" == "score" ]]; then
  gcc hungry_monkey_nogui.c move_monkey.c -o hungry_monkey
  ./hungry_monkey
else
  echo "next time choose a valid option"
fi

echo "thanks for playing"
