<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="ftApp.liveSharing.home.createOrEditLabel" v-text="$t('ftApp.liveSharing.home.createOrEditLabel')">Create or edit a LiveSharingFt</h2>
                <div>
                    <div class="form-group" v-if="liveSharing.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="liveSharing.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.liveSharing.name')" for="live-sharing-ft-name">Name</label>
                        <input type="text" class="form-control" name="name" id="live-sharing-ft-name"
                            :class="{'valid': !$v.liveSharing.name.$invalid, 'invalid': $v.liveSharing.name.$invalid }" v-model="$v.liveSharing.name.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.liveSharing.day')" for="live-sharing-ft-day">Day</label>
                        <div class="d-flex">
                            <input id="live-sharing-ft-day" type="datetime-local" class="form-control" name="day" :class="{'valid': !$v.liveSharing.day.$invalid, 'invalid': $v.liveSharing.day.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.liveSharing.day.$model)"
                            @change="updateInstantField('day', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.liveSharing.confNumber')" for="live-sharing-ft-confNumber">Conf Number</label>
                        <input type="text" class="form-control" name="confNumber" id="live-sharing-ft-confNumber"
                            :class="{'valid': !$v.liveSharing.confNumber.$invalid, 'invalid': $v.liveSharing.confNumber.$invalid }" v-model="$v.liveSharing.confNumber.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.liveSharing.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./live-sharing-ft-update.component.ts">
</script>
