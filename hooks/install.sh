#!/bin/bash
set -e

copyFile()
{
        echo "Copiando $1"
        cp $1 ./
}

CURRENT_DIRECTORY=$(pwd)
scripts=$(find . -maxdepth 1 ! -type d ! \( -name '*.sh' -o -name '*.md' -o -name '*.bat' \) | sed 's|^./||')

cd ../.git/hooks

for hook in $scripts
do      
        if [ -e $hook ]; then
                read -p "¿Ya existe un hook llamado $hook, quieres sobreescribirlo?(y/n): " replace
                if [ "$replace" = "y" ]; then copyFile $CURRENT_DIRECTORY/$hook; fi
        else    
                copyFile $CURRENT_DIRECTORY/$hook
        fi      
done

cd $CURRENT_DIRECTORY
