Benjamín Jimenez - Antonia Guajardo - Grupo 9

Enunciado:
“Se debe crear una pizarra múltiple que permita dibujar trazos, rectángulos, entidades y conectores UML mediante arrastre del mouse. La pizarra debe contar con
modos de edición, como borrar y crear las diferentes formas de UML. Para eliminar, se busca el elemento que contenga los píxeles encerrados por un rectángulo fantasma que
se crea entre el clic del mouse y la liberación del mismo. La pizarra múltiple (con un sistema de pestañas o un menú) consiste en tener una de ellas en el panel central en
un momento dado, pudiendo cambiar de una a otra sin perder los cambios realizados. Se debe poder guardar la pizarra múltiple en un archivo y cargarla posteriormente. Además,
se debe permitir borrar la pizarra completa. La pizarra múltiple se debe guardar automáticamente en el archivo al cambiar de una pizarra a otra. Por último, se debe poder 
elegir el color de las líneas y entidades mediante botones en la interfaz gráfica (GUI).”

Patrones utilizados:
Se utilizó el patrón Command, el cual es un patrón de comportamiento, para representar los comandos del menú. Se implementaron las clases NuevoCommand
y DeshacerCommand, cada una de ellas implementa la interfaz ActionListener y se encarga de ejecutar la acción correspondiente en la ventana principal. El uso de este patrón
otorgó una mayor flexibilidad para manejar y extender los comandos del menú, permitiendo agregar nuevos comandos y desacoplar el código del menú de las acciones específicas
que se ejecutan. A su vez el patrón Command permitió separar la lógica del menú y las acciones específicas que se ejecutan en la ventana principal. Cada comando encapsula
una acción específica, lo que facilita mantener y extraer código. También como se mencionó anteriormente permite desacoplar el código del menú por lo que el menú sólo
necesita conocer los comandos disponibles y no necesita conocer los detalles específicos de cómo se implementa el comando.
También se utilizó el patrón Observer, el cual es un patrón de comportamiento igualmente. Para la implementación de este patrón agregamos la lista actionListeners para 
mantener un registro de los observadores (ActionListeners) interesados en los cambios de la opción seleccionada. Se implementó el método registrarObservador() para agregar
un observador a la lista, y el método eliminarObservador() para eliminar un observador de la lista. También se agregó el método notificarObservadores() para notificar
a todos los observadores cuando se produce un cambio en la opción seleccionada. El uso de este patrón permite establecer una comunicación flexible y desacoplada entre
la clase BarraHerramientas y sus observadores, los ActionListeners. Nuevos observadores pueden ser agregados sin modificar la clase BarraHerramientas. Además, los
observadores pueden responder de manera independiente a los cambios en la opción seleccionada. Por último, cada vez que se selecciona una opción en la barra de herramientas,
se notifica a los observadores a través del método notificarObservadores(). Lo cual les permite realizar acciones específicas en respuesta al cambio de opción, sin acoplar
directamente la lógica de la barra de herramientas con los observadores.

Decisiones realizadas:
-Se tomó la decisión de utilizar la serialización de objetos para poder permitir que el programa guarde las pizarras.
-Se tomó la decisión de crear un botón para deshacer cambios hechos en la pizarra para facilitar el uso.
-Se tomó la decisión de utilizar la clase Graphics2D para facilitar la creación de figuras y su manipulación.

Problemas encontrados:
Hubo problemas con la ruta de acceso de las imágenes a la hora de utilizar Maven ya que las imágenes deben estar en la carpeta resources para que sean reconocidas.
Las imágenes se encontraban originalmente en una carpeta aparte pero luego fueron movidas y el problema fue solucionado.
Hubo problemas a la hora de crear las flechas ya que todas responden a la misma condición. Sin embargo se logró solucionar aplicando un Switch Case.

Bugs:
-El último texto creado tiende a no guardarse si además fue el último elemento en crearse.
-El texto suele tener problemas para crearse exactamente en la posición que uno selecciona inicialmente.
-En la parte de Archivos del menú los botones Guardar, Proyectos y Abrir no hacen nada.
-La goma a veces crea un cuadrado al ser selccionada si no hay nada que borrar. 
