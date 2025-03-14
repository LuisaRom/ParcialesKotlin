class Reservacion(
    val idReserva: Int,
    val idHuesped: Int,
    val nomHuesped: String,
    val emailHuesped: String,
    val numHabitacion: Int,
    val tipoHabitacion: String,
    val precioNoche: Int,
    val cantidadNoches: Int
) {
    fun calcularTotal(): Int {
        return precioNoche * cantidadNoches
    }

    companion object {
        private val reservas = mutableListOf<Reservacion>()
        private val habitacionesDisponibles = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        fun registrarReserva() {
            println("\nIngrese ID de reserva: ")
            val idReserva = readLine()?.toIntOrNull() ?: return

            println("Ingrese ID del huésped: ")
            val idHuesped = readLine()?.toIntOrNull() ?: return

            println("Ingrese nombre del huésped: ")
            val nomHuesped = readLine().orEmpty()

            println("Ingrese email del huésped: ")
            val emailHuesped = readLine().orEmpty()

            if (habitacionesDisponibles.isEmpty()) {
                println("No hay habitaciones disponibles.")
                return
            }

            // Mostrar habitaciones disponibles
            println("\nHabitaciones disponibles: ${habitacionesDisponibles.joinToString(", ")}")
            println("Seleccione el número de habitación: ")
            val numHabitacion = readLine()?.toIntOrNull()

            if (numHabitacion == null || numHabitacion !in habitacionesDisponibles) {
                println("Habitación no disponible.")
                return
            }

            println("\nSeleccione el tipo de habitación:")
            println("1. Estándar - $50.000/noche")
            println("2. Doble - $100.000/noche")
            println("3. Suite - $250.000/noche")

            val (tipoHabitacion, precioNoche) = when (readLine()?.toIntOrNull()) {
                1 -> "Estándar" to 50000
                2 -> "Doble" to 100000
                3 -> "Suite" to 25000
                else -> {
                    println("Opción inválida. Se asignará 'Estándar' por defecto.")
                    "Estándar" to 50000
                }
            }

            println("Ingrese cantidad de noches a reservar: ")
            val cantidadNoches = readLine()?.toIntOrNull() ?: return

            val nuevaReserva = Reservacion(
                idReserva, idHuesped, nomHuesped, emailHuesped,
                numHabitacion, tipoHabitacion, precioNoche, cantidadNoches
            )

            reservas.add(nuevaReserva)
            habitacionesDisponibles.remove(numHabitacion)
            println("Reserva registrada con éxito")
        }

        fun cancelarReserva() {
            println("\nIngrese ID de reserva a cancelar: ")
            val idReserva = readLine()?.toIntOrNull() ?: return

            val reserva = reservas.find { it.idReserva == idReserva }
            if (reserva != null) {
                reservas.remove(reserva)
                habitacionesDisponibles.add(reserva.numHabitacion)
                habitacionesDisponibles.sort()
                println("Reserva con ID $idReserva ha sido cancelada")
            } else {
                println("No se encontró la reserva con ID $idReserva.")
            }
        }

        fun mostrarReservasActivas() {
            if (reservas.isEmpty()) {
                println("No hay reservaciones")
            } else {
                println("\nReservaciones:")
                reservas.forEach {
                    println("ID: ${it.idReserva}, Huésped: ${it.nomHuesped}, Habitación: ${it.numHabitacion}, Tipo: ${it.tipoHabitacion}, Noches: ${it.cantidadNoches}, Total: ${it.calcularTotal()}")
                }
            }
        }
    }
}