<#assign
    known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
        merchant = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        username = merchant.getUsername()
        isAdmin = merchant.isAdmin()
        isOwner = merchant.isOwner()
    >
<#else>
    <#assign
        username = "unknown"
        isAdmin = false
        isOwner = false
    >
</#if>