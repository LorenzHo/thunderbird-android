package app.k9mail.feature.account.oauth

import app.k9mail.core.common.coreCommonModule
import app.k9mail.feature.account.oauth.data.AuthorizationRepository
import app.k9mail.feature.account.oauth.domain.DomainContract
import app.k9mail.feature.account.oauth.domain.DomainContract.UseCase
import app.k9mail.feature.account.oauth.domain.usecase.GetOAuthRequestIntent
import app.k9mail.feature.account.oauth.domain.usecase.SuggestServerName
import net.openid.appauth.AuthorizationService
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

val featureAccountOAuthModule: Module = module {
    includes(coreCommonModule)

    factory {
        AuthorizationService(
            androidApplication(),
        )
    }

    factory<DomainContract.AuthorizationRepository> {
        AuthorizationRepository(
            service = get(),
        )
    }

    factory<UseCase.SuggestServerName> { SuggestServerName() }

    factory<UseCase.GetOAuthRequestIntent> {
        GetOAuthRequestIntent(
            repository = get(),
            configurationProvider = get(),
        )
    }
}