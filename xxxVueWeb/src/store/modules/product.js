import { getTreeList } from '@/api/prodList'
import { getListData,data2treeDG,setTreeData } from '@/utils/category'

const getDefaultState = () => {
  return {
    treeList: ''
  }
}

const state = getDefaultState()

const mutations = {
  SET_TREELIST: (state, treeList) => {
    state.treeList = treeList
  }
}

const actions = {

  // 신카테고리 리스트 호출
  fecthTreeList({ commit }, payload) {
    
    return new Promise((resolve, reject) => {
      getTreeList().then(response => {
        
        console.log("fecthTreeList call")
        //console.log("response.data.treeModel =======>" + response.data.treeModel)

        //commit('SET_TREELIST', response.data.treeModel)
        
        let dataArray = []
        dataArray = getListData(response.data.treeModel)
        let treeInfo = ''
        treeInfo = data2treeDG(response.data.treeModel, dataArray)
        
        console.log('treeInfo ====>' + JSON.stringify(treeInfo))
        //commit('SET_TREELIST', treeInfo)
        setTreeData(treeInfo)

        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

