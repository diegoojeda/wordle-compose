package com.apiumhub.ktor_server

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

object MainServer {
    fun start() {
        embeddedServer(Netty, port = 8080) {
            install(ContentNegotiation) {
                json()
            }
            routing {
                get("/next_word") {
                    call.respond(
                        listOf(FIVE_LETTER_WORDS.random())
                    )
                }
            }
        }.start(wait = false)
    }
}

val FIVE_LETTER_WORDS = listOf(
    "sobre",
    "entre",
    "habia",
    "hasta",
    "desde",
    "puede",
    "todos",
    "parte",
    "tiene",
    "donde",
    "mismo",
    "ahora",
    "otros",
    "tanto",
    "segun",
    "menos",
    "mundo",
    "antes",
    "forma",
    "hacer",
    "estos",
    "mayor",
    "hacia",
    "ellos",
    "hecho",
    "mucho",
    "quien",
    "estan",
    "lugar",
    "otras",
    "mejor",
    "nuevo",
    "decir",
    "todas",
    "luego",
    "medio",
    "estas",
    "tenia",
    "nunca",
    "poder",
    "veces",
    "grupo",
    "misma",
    "nueva",
    "mujer",
    "cosas",
    "tener",
    "punto",
    "noche",
    "haber",
    "fuera",
    "usted",
    "nadie",
    "horas",
    "tarde",
    "estar",
    "padre",
    "gente",
    "final",
    "madre",
    "cinco",
    "siglo",
    "meses",
    "maria",
    "seria",
    "junto",
    "aquel",
    "dicho",
    "casos",
    "manos",
    "nivel",
    "podia",
    "largo",
    "falta",
    "hemos",
    "trata",
    "algun",
    "señor",
    "claro",
    "orden",
    "buena",
    "libro",
    "igual",
    "ellas",
    "total",
    "tengo",
    "unico",
    "pesar",
    "calle",
    "vista",
    "campo",
    "saber",
    "obras",
    "razon",
    "niños",
    "estoy",
    "quien",
    "fondo",
    "papel",
    "demas",
    "ambos",
    "salud",
    "media",
    "deben",
    "datos",
    "julio",
    "visto",
    "llego",
    "bueno",
    "joven",
    "hacia",
    "sigue",
    "cerca",
    "valor",
    "serie",
    "hijos",
    "juego",
    "epoca",
    "banco",
    "menor",
    "pasar",
    "queda",
    "hacen",
    "resto",
    "causa",
    "vamos",
    "apoyo",
    "civil",
    "pedro",
    "libre",
    "comun",
    "dejar",
    "salir",
    "union",
    "favor",
    "clase",
    "color",
    "decia",
    "quiza",
    "unica",
    "pueda",
    "lleva",
    "ayuda",
    "donde",
    "autor",
    "suelo",
    "viejo",
    "tomar",
    "siete",
    "lucha",
    "linea",
    "pocos",
    "norte",
    "cargo",
    "plaza",
    "poner",
    "viene",
    "radio",
    "puedo",
    "amigo",
    "habra",
    "santa",
    "sabia",
    "viaje",
    "vivir",
    "quedo",
    "exito",
    "carta",
    "miedo",
    "negro",
    "texto",
    "mitad",
    "fecha",
    "seran",
    "ideas",
    "llega",
    "lejos",
    "facil",
    "plazo",
    "enero",
    "atras",
    "chile",
    "fuego",
    "costa",
    "local",
    "habla",
    "tales",
    "sueño",
    "paris",
    "capaz",
    "podra",
    "dolor",
    "zonas",
    "temas",
    "junio",
    "marco",
    "mucha",
    "dicen",
    "busca",
    "abril",
    "lopez",
    "armas",
    "debia",
    "grado",
    "carne",
    "llama",
    "jorge",
    "corte",
    "etapa",
    "tipos",
    "deseo",
    "marzo",
    "jamas",
    "curso",
    "pablo",
    "larga",
    "lider",
    "torno",
    "somos",
    "cielo",
    "ambas",
    "perez",
    "doble",
    "crear",
    "casas",
    "lista",
    "leyes",
    "jesus",
    "grave",
    "tenga",
    "lunes",
    "junta",
    "estos",
    "sitio",
    "gusta",
    "clara",
    "moral",
    "gusto",
    "hotel",
    "salio",
    "nueve",
    "abajo",
    "estas",
    "venta",
    "ramon",
    "aires",
    "aguas",
    "dicha",
    "golpe",
    "pobre",
    "llevo",
    "coche",
    "leche",
    "tarea",
    "plata",
    "dando",
    "ganar",
    "calor",
    "suele",
    "miles",
    "ritmo",
    "pasos",
    "pesos",
    "plano",
    "jugar",
    "gesto",
    "vasco",
    "gomez",
    "pocas",
    "verde",
    "pidio",
    "comer",
    "fines",
    "labor",
    "justo",
    "actos",
    "museo",
    "pagar",
    "sabes",
    "areas",
    "santo",
    "vieja",
    "mario",
    "reina",
    "salvo",
    "quiso",
    "acaba",
    "marca",
    "pleno",
    "brazo",
    "acaso",
    "error",
    "seres",
    "poeta",
    "altos",
    "hojas",
    "darle",
    "clave",
    "votos",
    "logro",
    "sirve",
    "deuda",
    "feliz",
    "tanta",
    "mente",
    "breve",
    "firma",
    "jaime",
    "canal",
    "conde",
    "carga",
    "reyes",
    "abrir",
    "cuyos",
    "negra",
    "morir",
    "caida",
    "banda",
    "frase",
    "bases",
    "culpa",
    "entra",
    "hayan",
    "diego",
    "actor",
    "sacar",
    "murio",
    "estas",
    "saben",
    "corto",
    "david",
    "salon",
    "cifra",
    "bolsa",
    "fuese",
    "serio",
    "reino",
    "plena",
    "venia",
    "aznar",
    "legal",
    "abrio",
    "china",
    "dedos",
    "creer",
    "voces",
    "angel",
    "temor",
    "penso",
    "dudas",
    "lleno",
    "vacio",
    "ciclo",
    "valle",
    "llamo",
    "pecho",
    "honor",
    "pedir",
    "mirar",
    "clima",
    "punta",
    "posee",
    "entro",
    "pacto",
    "penal",
    "llena",
    "angel",
    "disco",
    "ideal",
    "artes",
    "villa",
    "venir",
    "miami",
    "ruido",
    "basta",
    "tabla",
    "avion",
    "cuyas",
    "hablo",
    "humor",
    "darse",
    "ganas",
    "dosis",
    "altas",
    "pared",
    "perro",
    "añade",
    "viven",
    "debio",
    "hogar",
    "pieza",
    "firme",
    "exige",
    "polvo",
    "luces",
    "virus",
    "nacio",
    "animo",
    "cesar",
    "gasto",
    "pausa",
    "esten",
    "playa",
    "horno",
    "japon",
    "anual",
    "norma",
    "tomas",
    "dulce",
    "mando",
    "chica",
    "unido",
    "acabo",
    "solar",
    "costo",
    "tesis",
    "toros",
    "ocupa",
    "patio",
    "corta",
    "señal",
    "paseo",
    "arena",
    "dejan",
    "barco",
    "signo",
    "arbol",
    "vemos",
    "oscar",
    "pista",
    "marta",
    "modos",
    "desea",
    "pasan",
    "vuelo",
    "silla",
    "chico",
    "conto",
    "feria",
    "rueda",
    "verse",
    "hecha",
    "ponen",
    "rojas",
    "matar",
    "motor",
    "rumbo",
    "trato",
    "pense",
    "creia",
    "borde",
    "metro",
    "creen",
    "dueño",
    "bajar",
    "rusia",
    "vidas",
    "subir",
    "droga",
    "bajas",
    "jefes",
    "vivia",
    "reloj",
    "elena",
    "danza",
    "notas",
    "suave",
    "fotos",
    "masas",
    "arroz",
    "islas",
    "goles",
    "fruto",
    "torre",
    "salas",
    "vital",
    "sabor",
    "tasas",
    "dieta",
    "andar",
    "pilar",
    "rival",
    "traje",
    "techo",
    "diria",
    "ricos",
    "salsa",
    "amiga",
    "haria",
    "vivos",
    "fidel",
    "india",
    "tocar",
    "bajos",
    "malos",
    "oeste",
    "rural",
    "nariz",
    "letra",
    "logra",
    "opera",
    "acido",
    "banca",
    "canto",
    "debil",
    "plato",
    "monte",
    "etica",
    "salen",
    "pujol",
    "daños",
    "salto",
    "moscu",
    "bomba",
    "surge",
    "oreja",
    "muñoz",
    "xviii",
    "calma",
    "baile",
    "queso",
    "mueve",
    "euros",
    "coste",
    "ronda",
    "kilos",
    "rigor",
    "ponia",
    "cerro",
    "palma",
    "turno",
    "grito",
    "deber",
    "ramas",
    "lento",
    "beber",
    "actua",
    "senti",
    "salia",
    "caido",
    "huevo",
    "corre",
    "juega",
    "trato",
    "vigor",
    "redes",
    "venga",
    "hagan",
    "bella",
    "daban",
    "sufre",
    "luisa",
    "regla",
    "poema",
    "limon",
    "dolar",
    "crees",
    "renta",
    "prima",
    "prisa",
    "cajas",
    "novia",
    "caras",
    "verlo",
    "nieve",
    "lados",
    "rubio",
    "echar",
    "quede",
    "suiza",
    "socio",
    "piano",
    "otoño",
    "leido",
    "prado",
    "halla",
    "jordi",
    "grasa",
    "menem",
    "parar",
    "unida",
    "irene",
    "nubes",
    "dices",
    "lanzo",
    "pesca",
    "solos",
    "selva",
    "falso",
    "aquel",
    "chino",
    "adios",
    "suyos",
    "culto",
    "guion",
    "niega",
    "envio",
    "crema",
    "situa",
    "filas",
    "nuñez",
    "balon",
    "muere",
    "hijas",
    "lucia",
    "ramos",
    "felix",
    "laura",
    "niñas",
    "malas",
    "vivio",
    "arias",
    "pagos",
    "caldo",
    "serlo",
    "quito",
    "rayos",
    "josep",
    "ancho",
    "aerea",
    "duque",
    "genes",
    "piden",
    "sofia",
    "trece",
    "penas",
    "viuda",
    "mesas",
    "fallo",
    "barra",
    "primo",
    "suena",
    "grito",
    "toman",
    "preve",
    "colon",
    "crece",
    "heroe",
    "rocas",
    "lenta",
    "llave",
    "haces",
    "ajeno",
    "hielo",
    "drama",
    "rango",
    "toque",
    "solas",
    "subio",
    "juana",
    "solia",
    "minas",
    "lanza",
    "rojos",
    "fases",
    "arabe",
    "falsa",
    "james",
    "verla",
    "metal",
    "reves",
    "ortiz",
    "silva",
    "evita",
    "ruben",
    "listo",
    "fraga",
    "nacer",
    "seria",
    "indio",
    "pasta",
    "parto",
    "aviso",
    "filme",
    "pollo",
    "duras",
    "noble",
    "bello",
    "vidal",
    "pelea",
    "rabia",
    "cinta",
    "muros",
    "copia",
    "cuota",
    "tramo",
    "barro",
    "cadiz",
    "haran",
    "ponga",
    "carro",
    "flujo",
    "hueso",
    "duros",
    "tumba",
    "diana",
    "medir",
    "presa",
    "apoya",
    "video",
    "volvi",
    "movil",
    "trama",
    "tenis",
    "vayan",
    "llevo",
    "creyo",
    "sexto",
    "bahia",
    "vinos",
    "rosas",
    "trajo",
    "cobre",
    "recta",
    "oliva",
    "patas",
    "novio",
    "justa",
    "barba",
    "acero",
    "genio",
    "vapor",
    "curva",
    "trate",
    "diera",
    "viena"
)