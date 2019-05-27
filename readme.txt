dev ibrahim estanga 
postulando a android develop

descripcion de movieDbApp

Arquitectura de desarrollo usado mvp 

lenguaje base Kotlin
librerias usadas: 
dagger 2 
retrofit 2 
rxjava 2

descripcion de las capas usadas 
con la ayuda de la libreria dagger 2 se pudo optimizar la creacion de la capa de red dividiendola en las siguientes clases 
	°networkModule que se encarga de proveer de los interceptores 
	manejo de cache y la configuracion del cliente httpOk

	° RetrofitModule  se encarga de preveer a la app las instacias ya configuradas de retrofit(configuradas con los clientes httpOk,base url,Gson convertes 
     	y adaptador para usar rxjava)

	°ApiModule su trabajo proveer las instancias retrofit con las interfaces destinadas para cada modulo de la app 
	
	°CallbackHandleObserver observa las peticiones echas y trata la respuesta 
	
	°modelMovie,ModelTVShow,ModelDetails se encargan de hacer las peticiones que les solicita su clase correspondiente de la capa de negocio 

capa de negocio 
		
	HomePresenter,TvShowPresenter,DetailsPresenter se encargan de hacer los request a la capa de red para las peticiones http y reciben los datos 
	tratan y direccionan la respuesta pertinente de igual forma manejan las operaciones de filtrado de sus respectivas vistas 	
	
capa de vista 
	
	esta capa tiene como miembro un activity y 3 fragment 
	°MovieFragmente se encarga de motrar al usuario todos los datos referente a las peliculas en sus 3 opciones 
	°tvShowFragmente se encarga de motrar al usuario todos los datos referente a las tvShow en sus 3 opciones 
	°activity es utilizado como contenedor y como puente para comunicar los datos entre las diferente instancias de los fragments
	de igual forma maneja el drawer y el navigation button

cada modulo tiene su capa proveedora  de los objetos necesarios para usar el presentador y el model de cada seccion
esta capa tiene las siguientes clases 
	°homeModule,TvShowModule,DetailsModule tiene como responsabilidad crear y proveer las instacias de los presentadores (negocio) y los model (red) 
	de sus secciones respectivas 
	°homeComponent,TvComponent,DetailsComponent se encargan de ser la interfaz que conecta sus respectivos modulos con el resto del grafo y con las vistas 
	donde seran inyectadas 

Respuestas a las preguntas señaladas en el correo

1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?
	r-> este principo conciste en que cada funcion y objeto tenga una unica responsabilidad con el objetivo de hacer mas viable los unit test (princios solid)

2. Qué características tiene, según su opinión, un “buen” código o código limpio?
	r-> un codigo limpio para mi es aquel que primero repite la menor cantidad posible de codigo es decir mientras sea posible no repetir codigo en segunda instancia 
	que este comentado al menos en los metodos que sean de mayor complejidad. Otra cosa importate para mi son los nombre de la variables y las funciones es decir 
	al leer el nombre de la variable o de la funcion ya se debe dar una idea de su responsabilidad en el codigo

