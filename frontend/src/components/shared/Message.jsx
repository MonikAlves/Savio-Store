import styles from "./Message.module.css"

const Message = ({message, type}) => {
  return (
    <div className= {type == "error" ? styles.error: styles.sucess}> 
        <p>{message}</p>
    </div>
  )
}

export default Message