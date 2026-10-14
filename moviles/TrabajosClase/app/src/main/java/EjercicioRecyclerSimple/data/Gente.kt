package EjercicioRecyclerSimple.data

import EjercicioRecyclerSimple.domain.model.Autistas

object Gente {

    private val gente = mutableListOf(Autistas("CRISTOPERRO",20,"SI"),
                                      Autistas("JavingCount",10,"si"),
                                      Autistas("Lolito",25,"si")
    )

    fun dameAutistaPrincipal() = gente[0]
    fun dameLista(): List<Autistas> = gente.toList()

    fun addGente(autista: Autistas){
        gente.add(autista)
    }

    fun deleteGente(autista: Autistas){
        gente.remove(autista)
    }
}