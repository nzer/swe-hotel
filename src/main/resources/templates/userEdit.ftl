<#import "parts/common.ftl" as c>

<@c.page>
User editor
<form action="/merchant" method="post">
    <input type="text" name="username" value="${merchant.username}">
    <#list roles as role>
    <div>
        <label><input type="checkbox" name="${role}" ${merchant.roles?seq_contains(role)?string("checked","")}>${role}</label>
    </div>
    </#list>
    <input type="hidden" name="userId" value="${merchant.id}">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button type="submit">Save</button>
</form>
</@c.page>