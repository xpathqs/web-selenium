package org.xpathqs.web.selenium.executor.e2e.github.base

import org.xpathqs.core.selector.NullSelector
import org.xpathqs.core.selector.base.ISelector
import org.xpathqs.driver.navigation.base.ILoadable
import org.xpathqs.driver.navigation.base.ILoadableDelegate
import org.xpathqs.driver.navigation.base.INavigableDetermination
import org.xpathqs.driver.navigation.base.INavigableDeterminationDelegate
import org.xpathqs.driver.navigation.impl.Loadable
import org.xpathqs.driver.navigation.impl.NavigableDetermination
import org.xpathqs.core.selector.block.Block as CoreBlock

open class Block(
    base: ISelector = NullSelector()
) : CoreBlock(base), INavigableDeterminationDelegate, ILoadableDelegate {

    override fun afterReflectionParse() {
        GitHubNavigator.register(this)
    }

    override val nav: INavigableDetermination
            = NavigableDetermination(GitHubNavigator, this)

    override val loadable: ILoadable
            = Loadable(this)
}