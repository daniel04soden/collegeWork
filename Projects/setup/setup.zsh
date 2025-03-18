echo "Welcome Daniel...\n"
sleep 3

echo "This is our macos setup process\n"

echo "Setting up ohmyzsh\n"
sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"
git clone --depth=1 https://github.com/romkatv/powerlevel10k.git "${ZSH_CUSTOM:-$HOME/.oh-my-zsh/custom}/themes/powerlevel10k"

echo "Installing xcode tools\n"
xcode-select --install

echo "Installing homebrew\n"
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
echo "Make sure to add to path - may add manually"
 

