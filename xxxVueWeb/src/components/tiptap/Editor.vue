<template>
  <div class="editor" v-if="editor">
    <menu-bar class="editor__header" :editor="editor" />
    <!-- 색상선택 -->
    <input
      type="color"
      @input="
        editor
          .chain()
          .focus()
          .setColor($event.target.value)
          .run()
      "
      :value="editor.getAttributes('textStyle').color"
    />
    <!-- 폰트 크기 -->
    <floating-menu
      class="floating-menu"
      :tippy-options="{ duration: 100 }"
      :editor="editor"
      v-if="editor" 
    >
      <button
        @click="
          editor
            .chain()
            .focus()
            .toggleHeading({ level: 1 })
            .run()
        "
        :class="{ 'is-active': editor.isActive('heading', { level: 1 }) }"
      >
        H1
      </button>
      <button
        @click="
          editor
            .chain()
            .focus()
            .toggleHeading({ level: 2 })
            .run()
        "
        :class="{ 'is-active': editor.isActive('heading', { level: 2 }) }"
      >
        H2
      </button>
      <button
        @click="
          editor
            .chain()
            .focus()
            .toggleBulletList()
            .run()
        "
        :class="{ 'is-active': editor.isActive('bulletList') }"
      >
        Bullet List
      </button>
    </floating-menu>
    <!-- 폰트 선택 -->

    <editor-content class="editor__content" :editor="editor" />
  </div>
</template>

<script>
import { Editor, EditorContent, FloatingMenu } from "@tiptap/vue-3";
import StarterKit from "@tiptap/starter-kit";
import TaskList from "@tiptap/extension-task-list";
import TaskItem from "@tiptap/extension-task-item";
import Highlight from "@tiptap/extension-highlight";
import CharacterCount from "@tiptap/extension-character-count";
import TextStyle from "@tiptap/extension-text-style";
import MenuBar from "@/components/tiptap/MenuBar.vue";

import { Color } from "@tiptap/extension-color";
export default {
  components: {
    EditorContent,
    FloatingMenu,
    MenuBar
  },

  data() {
    return {
      editor: null
    };
  },
  mounted() {
    this.editor = new Editor({
      extensions: [
        StarterKit,
        Highlight,
        TaskList,
        TaskItem,
        CharacterCount.configure({
          limit: 10000
        }),
        TextStyle,
        Color
      ],
      content: `
        <h2>
          Hi there,
        </h2>
        <p>
          this is a <em>basic</em> example of <strong>tiptap</strong>. Sure, there are all kind of basic text styles you’d probably expect from a text editor. But wait until you see the lists:
        </p>
        <p>
          Isn’t that great? And all of that is editable. But wait, there’s more. Let’s try a code block:
        </p>

        <p>
          I know, I know, this is impressive. It’s only the tip of the iceberg though. Give it a try and click a little bit around. Don’t forget to check the other examples too.
        </p>
        <blockquote>
          Wow, that’s amazing. Good work, boy! 👏
          <br />
          — Mom
        </blockquote>
      `
    });
  },

  methods: {
    beforeUnmount() {
      this.editor.destroy();
    }
  }
};
</script>
<!--
<style lang="scss">
// .editor {
//   display: flex;
//   flex-direction: column;
//   max-height: 26rem;
//   color: #0d0d0d;
//   background-color: #fff;
//   border: 3px solid #0d0d0d;
//   border-radius: 0.75rem;

//   &__header {
//     display: flex;
//     align-items: center;
//     flex: 0 0 auto;
//     flex-wrap: wrap;
//     padding: 0.25rem;
//     border-bottom: 3px solid #0d0d0d;
//   }

//   &__content {
//     padding: 1.25rem 1rem;
//     flex: 1 1 auto;
//     overflow-x: hidden;
//     overflow-y: auto;
//     -webkit-overflow-scrolling: touch;
//   }

//   &__footer {
//     display: flex;
//     flex: 0 0 auto;
//     align-items: center;
//     justify-content: space-between;
//     flex-wrap: wrap;
//     white-space: nowrap;
//     border-top: 3px solid #0d0d0d;
//     font-size: 12px;
//     font-weight: 600;
//     color: #0d0d0d;
//     white-space: nowrap;
//     padding: 0.25rem 0.75rem;
//   }

//   /* Some information about the status */
//   &__status {
//     display: flex;
//     align-items: center;
//     border-radius: 5px;

//     &::before {
//       content: " ";
//       flex: 0 0 auto;
//       display: inline-block;
//       width: 0.5rem;
//       height: 0.5rem;
//       background: rgba(#0d0d0d, 0.5);
//       border-radius: 50%;
//       margin-right: 0.5rem;
//     }

//     &--connecting::before {
//       background: #616161;
//     }

//     &--connected::before {
//       background: #b9f18d;
//     }
//   }

//   &__name {
//     button {
//       background: none;
//       border: none;
//       font: inherit;
//       font-size: 12px;
//       font-weight: 600;
//       color: #0d0d0d;
//       border-radius: 0.4rem;
//       padding: 0.25rem 0.5rem;

//       &:hover {
//         color: #fff;
//         background-color: #0d0d0d;
//       }
//     }
//   }
// }
</style>

//
<style lang="scss">
// /* Give a remote user a caret */
// .collaboration-cursor__caret {
//   position: relative;
//   margin-left: -1px;
//   margin-right: -1px;
//   border-left: 1px solid #0d0d0d;
//   border-right: 1px solid #0d0d0d;
//   word-break: normal;
//   pointer-events: none;
// }

// /* Render the username above the caret */
// .collaboration-cursor__label {
//   position: absolute;
//   top: -1.4em;
//   left: -1px;
//   font-size: 12px;
//   font-style: normal;
//   font-weight: 600;
//   line-height: normal;
//   user-select: none;
//   color: #0d0d0d;
//   padding: 0.1rem 0.3rem;
//   border-radius: 3px 3px 3px 0;
//   white-space: nowrap;
// }

// /* Basic editor styles */
// .ProseMirror {
//   > * + * {
//     margin-top: 0.75em;
//   }

//   ul,
//   ol {
//     padding: 0 1rem;
//   }

//   h1,
//   h2,
//   h3,
//   h4,
//   h5,
//   h6 {
//     line-height: 1.1;
//   }

//   code {
//     background-color: rgba(#616161, 0.1);
//     color: #616161;
//   }

//   pre {
//     background: #0d0d0d;
//     color: #fff;
//     font-family: "JetBrainsMono", monospace;
//     padding: 0.75rem 1rem;
//     border-radius: 0.5rem;

//     code {
//       color: inherit;
//       padding: 0;
//       background: none;
//       font-size: 0.8rem;
//     }
//   }

//   mark {
//     background-color: #faf594;
//   }

//   img {
//     max-width: 100%;
//     height: auto;
//   }

//   hr {
//     margin: 1rem 0;
//   }

//   blockquote {
//     padding-left: 1rem;
//     border-left: 2px solid rgba(#0d0d0d, 0.1);
//   }

//   hr {
//     border: none;
//     border-top: 2px solid rgba(#0d0d0d, 0.1);
//     margin: 2rem 0;
//   }

//   ul[data-type="taskList"] {
//     list-style: none;
//     padding: 0;

//     li {
//       display: flex;
//       align-items: center;

//       > label {
//         flex: 0 0 auto;
//         margin-right: 0.5rem;
//       }
//     }
//   }
// }
//
</style>
-->
