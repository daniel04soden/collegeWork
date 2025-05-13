set number                  
set nocompatible              
syntax on                     
filetype plugin indent on     
set tabstop=4                 " Sets the number of spaces that a <Tab> character equates to.
set shiftwidth=4              " Sets the number of spaces used for each step of (auto)indent.
set expandtab                 " Converts tabs to spaces.
set autoindent                " Uses the indent from the previous line when starting a new line.
set smartindent               " Smart autoindenting based on syntax.
set mouse=a                   " Enables mouse support in all modes.
set hidden                    " Allows you to switch buffers without saving.
set incsearch                 " Highlights matches while typing the search.
set ignorecase                " Ignores case when searching…
set smartcase                 " …unless the search pattern contains uppercase letters.

let mapleader="\<Space>"


if empty(glob('~/.vim/autoload/plug.vim'))
  silent !curl -fLo ~/.vim/autoload/plug.vim --create-dirs
    \ https://raw.githubusercontent.com/junegunn/vim-plug/master/plug.vim
  autocmd VimEnter * PlugInstall --sync | source $MYVIMRC
endif

call plug#begin('~/.vim/plugged')
Plug 'jiangmiao/auto-pairs'
Plug 'neoclide/coc.nvim', {'branch': 'release'}
Plug 'preservim/nerdtree'
Plug 'tpope/vim-fugitive'
Plug 'airblade/vim-gitgutter'
Plug 'itchyny/lightline.vim'
Plug 'sheerun/vim-polyglot'
Plug 'tpope/vim-commentary'
Plug 'dracula/vim', { 'as': 'dracula' }
call plug#end()

" Lightline with git status & Powerline Seperators
let g:lightline = {
      \'colorscheme': 'dracula',
  \ 'separator': { 'left': '', 'right': '' },
  \ 'subseparator': { 'left': '', 'right': '' },
  \ 'active': {
  \   'left': [ [ 'mode', 'paste' ],
  \             [ 'gitbranch', 'readonly', 'filename', 'modified' ] ]
  \ },
  \ 'component_function': {
  \   'gitbranch': 'FugitiveHead'
  \ }
  \ }

" Always show the status line (0 = never, 1 = only if more than one window).
set laststatus=2

" Toggle NERDTree with <Space>fe.
nnoremap <silent> <Leader>fe :NERDTreeToggle<CR>
" Move focus between splits with Ctrl-h and Ctrl-l.
nnoremap <silent> <C-h> :wincmd h<CR>
nnoremap <silent> <C-l> :wincmd l<CR>

" Enables GitGutter by default (shows changes in the gutter).
let g:gitgutter_enabled = 1

" Use `[g` and `]g` to navigate diagnostics
nmap <silent> [g <Plug>(coc-diagnostic-prev)
nmap <silent> ]g <Plug>(coc-diagnostic-next)

" GoTo code navigation
nmap <silent> gd <Plug>(coc-definition)
nmap <silent> gy <Plug>(coc-type-definition)
nmap <silent> gi <Plug>(coc-implementation)
nmap <silent> gr <Plug>(coc-references)

" Use Tab for completion (if popup is visible), otherwise insert a tab
inoremap <silent><expr> <Tab>
  \ pumvisible() ? coc#_select_confirm() :
  \ "\<Tab>"

" Use Shift-Tab to go to previous item in the popup
inoremap <silent><expr> <S-Tab> pumvisible() ? "\<C-p>" : "\<C-h>"

" Auto install language servers (Rust, JS, HTML)
let g:coc_global_extensions = [
  \ 'coc-rust-analyzer',
  \ 'coc-html',
  \ 'coc-clangd',
  \ 'coc-go',
  \ 'coc-java'
  \ ]

" Auto-sources .vimrc whenever you save it.
autocmd BufWritePost *.vim source $MYVIMRC
" Shortens the idle time before triggering things like GitGutter or CursorHold events.
set updatetime=300
" Reduces verbosity in messages, especially related to completion.
set shortmess+=c
colorscheme dracula
