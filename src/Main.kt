
fun main() {

    var opcion: Int
    do {
        println("\nBIENVENIDO AL HOTEL")
        println("1. Registrar Reservación")
        println("2. Cancelar Reservación")
        println("3. Mostrar Reservaciones")
        println("4. Salir")
        print("Seleccione una opción: ")

        opcion = readLine()?.toIntOrNull() ?: 0

        when (opcion) {
            1 -> Reservacion.registrarReserva()
            2 -> Reservacion.cancelarReserva()
            3 -> Reservacion.mostrarReservasActivas()
            4 -> println("Saliendo del sistema de reservas.")
            else -> println("Opción no válida, intente nuevamente.")
        }
    } while (opcion != 4)
}