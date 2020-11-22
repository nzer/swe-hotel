<#import "parts/common.ftl" as c>

<@c.page>
    <div class="row">
        <div class="col-6">
            <h4>Seasons list</h4>
            <#list seasons as s>
                <div class="adminCard">
                    <p>Name: ${s.name}</p>
                    <p>Start: ${s.start}</p>
                    <p>End: ${s.end}</p>
                </div>
            </#list>
        </div>
        <div class="col-6">
            <h4>Add new</h4>
            <div id="season-form">
                <div class="form-group">
                    <form method="post" enctype="multipart/form-data">
                        <div class="modal-body">
                            <div class="form-group">
                                <input type="text" name="name" class="form-control" placeholder="Name" />
                            </div>
                            <div class="form-group">
                                <input type="date" name="start" class="form-control" placeholder="Start">
                            </div>
                            <div class="form-group">
                                <input type="date" name="end" class="form-control" placeholder="End">
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

