# Patrones de Arquitectura en Android
[![forthebadge](https://forthebadge.com/images/badges/built-by-developers.svg)](https://forthebadge.com) [![forthebadge](https://forthebadge.com/images/badges/for-sharks.svg)](https://forthebadge.com)

Existen para ayudarlo a diseñar su app, de tal manera que permita mantener la app a medida crece. En palabras sencillas, un patrón de arquitectura es la forma de describir como divides tu código.

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

[Aquí](https://medium.com/@fahedhermoza/por-qu%C3%A9-no-funciona-mvc-en-android-d0b747a823c0) puedes encontrar un post en detalle que responde esta pregunta.

# MVP
Este patrón de arquitectura se compone de las siguientes partes:

- Modelo: Responsable de recuperar datos, almacenarlos y cambiarlos.

- Vista: Responsable de mostrar la UI, este rol se designa a una Activity o Fragment. La vista ocultara y mostrara  vistas, manejara la navegación a otras actividades a través de Intents y escuchara las interacciones del sistema operativo y la entrada del usuario.

- Presentador: El presentador es la clase que habla tanto con el Modelo como con la Vista. Cualquier código que no maneje directamente la UI u otra lógica especifica del marco de Android, debe moverse de la Vista a la clase Presentador. 

Lógica de Presentación: Cualquier mapeo o formateo adicional de los datos es responsabilidad del Presentador.

MVP hace uso de interfaces para cumplir el objetivo de evitar referenciar a una clase de Android cuando escribe pruebas unitarias.

[Aquí](https://medium.com/@fahedhermoza/android-y-el-patr%C3%B3n-mvp-4b9ddf377185) puedes encontrar un post con mayor detalle sobre el patrón MVP.
# MVVM
Este patrón de arquitectura basado en flujos observables se compone de las siguientes partes:

- Modelo(DataModel): Recupera información de su fuente de datos y lo expone a los ViewModels.

- Vista: Muestra la UI e informa a las otras capas sobre acciones del usuario.

- ViewModel: Recupera información necesaria del Modelo, aplica las operaciones necesarias y expone los datos relevantes a través de eventos que las Vistas pueden observar.

El ViewModel no contiene referencia a la Vista, solo proporciona información y no le interesa quien lo consuma.

El problema de estados de la app lo soluciona de manera optima con los Componentes de Arquitectura de Android:

- ViewModel: Diseñada para administrar y almacenar información de manera consciente del ciclo de vida.

- LiveData: Permite que cualquier Vista observe cualquier cambio en los datos.

Generalmente se comunica la Vista y el ViewModel con observables(RxJava, LiveData o DataBinding)

[Aquí]() puedes encontrar un post con mayor detalle sobre el patrón MVVM.
# Implementación
El código de ejemplo se basa en una copia pirata de Evernote en el cual puedes organizar tus anotaciones. El diseño es el mismo para todas las apps solo cambia la implementación.
- MVC + Room + RxJava
- [x] [Código](https://github.com/FahedHermoza/PatronesDeArquitectura-Android/tree/master/MVC/ExampleNote01)
- MVP + Room + RxJava + Mockito 
- [x] [Código](https://github.com/FahedHermoza/PatronesDeArquitectura-Android/tree/master/MVP/ExampleNote01)
- MVVM + Room + Lifecycle + Mockito  
- [x] [Código](https://github.com/FahedHermoza/PatronesDeArquitectura-Android/tree/master/MVP/ExampleNote01)
- MVVM + Room + Lifecycle + Mockito + di  
- [x] [Código](https://github.com/FahedHermoza/PatronesDeArquitectura-Android/tree/master/MVVM/di/ExampleNote01)
- MVVM + Room + Lifecycle + Mockito + Koin  
- [x] [Código](https://github.com/FahedHermoza/PatronesDeArquitectura-Android/tree/master/MVVM/Koin/ExampleNote01)
- MVVM + Room + Lifecycle + Mockito + Koin +  KoinTest  
- [x] [Código](https://github.com/FahedHermoza/PatronesDeArquitectura-Android/tree/master/MVVM/KoinTest/ExampleNote01)

Extras: 
- Pokedex: MVVM + Retrofit + Lifecycle + Mockito + Koin + Piccaso  
- [x] [Código](https://github.com/FahedHermoza/PatronesDeArquitectura-Android/tree/master/EXTRA/MainPokemon)

# Diapositivas
- Slide : [URL](https://docs.google.com/presentation/d/12WxYEk32VPTVF8hCRSp0HJJJQKxwIX1Dwkk7ChVrqOc/edit?usp=sharing)

# Desarrollador
**Si te sirvió** puedes darme manito arriba en mi [blog](https://www.facebook.com/fahedhermoza/).