# Patrones de Arquitectura en Android
[![forthebadge](https://forthebadge.com/images/badges/built-by-developers.svg)](https://forthebadge.com) [![forthebadge](https://forthebadge.com/images/badges/for-sharks.svg)](https://forthebadge.com)

Existen para ayudarlo a diseñar su app, de tal manera que permita mantener la app a medida crece. 

Su objetivo se centra en 2 conceptos:
- Separación de Interfaces: Se relaciona con la separación de componentes por responsabilidad.
- Pruebas Unitarias: A medida que crece, probara su app para asegurarse de no romper la lógica de las funcionalidades existentes.

También llamadas patrones de presentación o patrones de UI (depende del autor). Los patrones de arquitecturas son MVC, MVP, MVVM, MVI, VIPER. Entonces nos preguntamos ya que existen tantos ¿Cuál es el mejor? Pues depende de su app y sus necesidades.

# MVC
Este patrón de arquitectura separa los componentes de un sistema de software en función de las responsabilidades. Tiene 3 componentes con responsabilidades distinta:

- Modelo: Es la capa de datos que incluye los objetos de datos, las clases de BD y otras lógicas de negocio, relacionadas con el almacenamiento de datos (remoto, local, memoria, sistema de archivos, Shared Preferences y otros).

- Vista: Es responsable de representar los datos de una manera legible para las personas a través de una pantalla.

- Controlador: Encapsula la lógica del sistema y controla tanto el Modelo como la Vista. El usuario interactúa con el sistema a través del controlador.

### ¿Por qué no funciona MVC?
El patrón MVC en un principio se aplico a Android, con el uso los desarrolladores  se dieron cuenta de que esto no funcionaba principalmente porque la Activity sirve como Controlador y Vista, esto conlleva problemas importantes para la separación de responsabilidades y pruebas de unidad. 

[Aquí](https://medium.com/@fahedhermoza/por-qu%C3%A9-no-funciona-mvc-en-android-d0b747a823c0) puedes encontrar una post en detalle que responde esta pregunta.

### App con MVC
[Aquí]() puedes encontrar un app construida con el patron de arquitectura MVC

# Mas información
Resumen del libro [Advanced Android App Architecture](https://store.raywenderlich.com/products/advanced-android-app-architecture).

## Desarrollador
**Si te sirvió** puedes darme manito arriba en mi [blog](https://www.facebook.com/fahedhermoza/).