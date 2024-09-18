package com.example.mybrickset.domain.usecase.local

class LocalUseCase (
    val insertSetCollection: InsertSetCollection,
    val deleteSetCollection: DeleteSetColleciton,
    val getAllSetCollection: GetAllSetCollection,
    val getAllSetCollectionOrderByPriceAsc: GetAllSetCollectionOrderByPriceAsc,
    val getAllSetCollectionOrderByPriceDesc: GetAllSetCollectionOrderByPriceDesc,
    val getAllSetCollectionOrderByDateAsc: GetAllSetCollectionOrderByDateAsc,
    val getAllSetCollectionOrderByDateDesc: GetAllSetCollectionOrderByDateDesc,
    val getSumPrice: GetSumPrice,
    val getSetCount: GetSetCount
)