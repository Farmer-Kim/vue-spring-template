<template>
<span>
	<jqx-loader
		ref="jqxLoader"
		:imagePosition="'top'"
		:width="100"
		:height="60"
	/>
</span>
</template>

<script>
import JqxLoader from "jqwidgets-scripts/jqwidgets-vue/vue_jqxloader.vue";

export default {
	name: 'loading-bar',

	components: {
		JqxLoader
	},

	data() {
		return {}
	},

	created() {},

	methods: {
		open() {
			this.$refs.jqxLoader.open();
		},
		close() {
			this.$refs.jqxLoader.close();
		}
	}
}
</script>
