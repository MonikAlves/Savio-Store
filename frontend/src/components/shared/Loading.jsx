import React from 'react'
import styles from './stylesShared/Loading.module.css'
import imagemCarrinho from './../../../public/5412611.png'
import imagemCaixas from './../../../public/caixa1.png'

const Loading = ({isOpen, src1, src2}) => {
  
    if(!isOpen)
        return null

    return (
    <div className='h-full w-full absolute z-50'>
        <div className={styles.Modal}>
            <img src= {imagemCarrinho} alt="" className={styles.Carrinho}/>
            <img src={imagemCaixas} alt="" className={styles.Caixa}/>
        </div>
    </div>
  )
}

export default Loading