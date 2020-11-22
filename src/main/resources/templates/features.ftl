<#import "parts/common.ftl" as c>

<@c.page>
    <div class="row">
        <div class="col-6">
            <h4>Rooms list</h4>
            <#list features as f>
                <div class="adminCard">
                    <p>Name: ${f.name}</p>
                </div>
            </#list>
        </div>
        <div class="col-6">
            <h4>Add new</h4>
            <div id="season-form">
                <div class="form-group">
                    <form method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="text" name="name" class="form-control" placeholder="Name">
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary"><b>Сохранить</b></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</@c.page>

