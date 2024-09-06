package com.example.mybrickset.domain.usecase.local

class LocalUseCase (
    val insertSetCollection: InsertSetCollection,
    val deleteSetCollection: DeleteSetColleciton,
    val getAllSetCollection: GetAllSetCollection,
    val getSumPrice: GetSumPrice,
    val getSetCount: GetSetCount
)