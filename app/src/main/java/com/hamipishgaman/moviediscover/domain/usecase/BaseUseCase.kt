package com.hamipishgaman.moviediscover.domain.usecase

interface BaseUseCase<inPut, outPut> {

    suspend fun execute(input: inPut): outPut
}