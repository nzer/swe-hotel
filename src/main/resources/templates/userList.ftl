<#import "parts/common.ftl" as c>

<@c.page>
List of merchants
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Role</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list merchants as merchant>
        <tr>
            <td>${merchant.username}</td>
            <td><#list merchant.roles as role>${role}<#sep>, </#list></td>
            <td><a href="/merchant/${merchant.id}">edit</a></td>
        </tr>
    </#list>
</table>
</@c.page>