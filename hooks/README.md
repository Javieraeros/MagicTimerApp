## Git hooks
La finalidad de los [git hooks](https://git-scm.com/book/en/v2/Customizing-Git-Git-Hooks) es automatizar cualquier tipo 
de tarea que queramos ejecutar antes de una acción en git. Su uso es tan sencillo como copiar cualquier git-hook a la 
carpeta `.git/hooks`, y una vez copiado, el hook se activará en cuanto la acción asociada (commit, push, rebase...) 
se ejecute o bien en la terminal, en el IDE o en cualquier interfaz gráfica de git.

Una vez copiados los hooks en la carpeta correspondiente, no tendremos que hacer nada más para que se ejecuten, 
simplemente se ejecutarán en el momento adecuado

### Linux / MacOS
Para copiar los git-hooks, solo tenemos que irnos al directorio `hooks` y ejecutar `sh install.sh`


#### Prepare-commit-msg
Hook encargado de que el mensaje del commit tenga un tag del timpo [VIEW-1]

Para evitar que se compruebe el mensaje del commit, se usaria: git -c hooks.disabled commit -m "MENSAJE QUE NO QUEREMOS QUE SE COMPRUEBE"


#### Pre-commit
Hook encargado de comprobar que se cumplen las reglas de análisis de código estático.
Para evitar que se hagan dichas comprobaciones y agilizar el tiempo de commit, se propone usar `--no-verify`, y de esta forma el hook no se desencadene

#### Pre-push
Hook encargado de comprobar que la aplicación en la que estamos trabajando cumple con los tests y compila correctamente generando el apk correspondiente.
Para que el hook cumpla su función debemos añadir una variable de entorno a nuestra máquina del tipo:


Si queremos evitar que se active este hook, basta con que escribamos `git push --no-verify ....`


Para más información, contactar con <fjruiz.1992@gmail.com>
