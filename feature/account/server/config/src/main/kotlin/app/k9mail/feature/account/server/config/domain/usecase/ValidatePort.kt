package app.k9mail.feature.account.server.config.domain.usecase

import app.k9mail.core.common.domain.usecase.validation.ValidationError
import app.k9mail.core.common.domain.usecase.validation.ValidationResult
import app.k9mail.feature.account.server.config.domain.ServerConfigDomainContract.UseCase

internal class ValidatePort : UseCase.ValidatePort {

    override fun execute(port: Long?): ValidationResult {
        return when (port) {
            null -> ValidationResult.Failure(ValidatePortError.EmptyPort)
            in MIN_PORT_NUMBER..MAX_PORT_NUMBER -> ValidationResult.Success
            else -> ValidationResult.Failure(ValidatePortError.InvalidPort)
        }
    }

    sealed interface ValidatePortError : ValidationError {
        object EmptyPort : ValidatePortError
        object InvalidPort : ValidatePortError
    }

    companion object {
        const val MAX_PORT_NUMBER = 65535
        const val MIN_PORT_NUMBER = 1
    }
}