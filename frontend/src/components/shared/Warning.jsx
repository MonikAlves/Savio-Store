import React from 'react'
import styles from "./stylesShared/Warning.module.css"

const Warning = ({message}) => {

  return (
    <div className='absolute inset-0 bg-black bg-opacity-75 flex items-center justify-center z-50 rounded flex-col stroke-black'>
            <span className={`flex text-2xl text-[#e05555] font-bold text-center border border-white bg-white rounded mx-5 p-5 ${styles.BordaTexto}`}>{message}</span>
    </div>
  )
}

export default Warning

