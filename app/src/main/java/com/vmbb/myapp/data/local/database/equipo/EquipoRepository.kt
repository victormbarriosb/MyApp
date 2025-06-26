package com.vmbb.myapp.data.local.database.equipo

import com.vmbb.myapp.data.local.estandar.DefaultBaseRepository
import javax.inject.Inject

class EquipoRepository @Inject constructor(
    dao: EquipoDao
) : DefaultBaseRepository<Equipo>(
    dao = dao,
    getAll = { dao.getAllFlow() }
)